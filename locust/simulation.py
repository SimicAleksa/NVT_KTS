import requests
import json

from locust import HttpUser, task, between, events
from random import randrange


start_and_end_points = [
    (45.235866, 19.807387),     # Djordja Mikeša 2
    (45.247309, 19.796717),     # Andje Rankovic 2
    (45.259711, 19.809787),     # Veselina Maslese 62
    (45.261421, 19.823026),     # Jovana Hranilovica 2
    (45.265435, 19.847805),     # Bele njive 24
    (45.255521, 19.845071),     # Njegoseva 2
    (45.249241, 19.852152),     # Stevana Musica 20
    (45.242509, 19.844632),     # Boska Buhe 10A
    (45.254366, 19.861088),     # Strosmajerova 2
    (45.223481, 19.847990)      # Gajeva 2
]


taxi_stops = [
    (45.238548, 19.848225),   # Stajaliste na keju
    (45.243097, 19.836284),   # Stajaliste kod limanske pijace
    (45.256863, 19.844129),   # Stajaliste kod trifkovicevog trga
    (45.255055, 19.810161),   # Stajaliste na telepu
    (45.246540, 19.849282),    # Stajaliste kod velike menze
    (45.2457403, 19.8313000), # puskinova 6
    (45.2407397, 19.8450102), #dragise brasovana 10
    (45.2539584, 19.8026196), #kace dejanovic 2
    (45.2477997, 19.7941289), #stevana calenica 2
    (45.2396372, 19.8028856), #prvomajska 9 
    (45.2321277, 19.8089044) # karas pala 3


]


license_plates = [
    'NS-001-AA',
    'NS-001-AB',
    'NS-001-AC',
    'NS-001-CA',
    'NS-001-CB',
    'NS-011-CC',
    'NS-021-CA',
    'NS-031-CB',
    'NS-041-CC',
    'NS-101-AA',
    'NS-101-AB',
    'NS-101-AC',
    'NS-101-CA',
    'NS-101-CB',
    'NS-111-CC',
    'NS-121-CA',
    'NS-131-CB',
    'NS-241-CC'
]

counter = 0


@events.test_start.add_listener
def on_test_start(environment, **kwargs):
    requests.delete('http://localhost:8000/api/rides')
    requests.delete('http://localhost:8000/api/drivers')


class QuickstartUser(HttpUser):
    host = 'http://localhost:8000'
    wait_time = between(0.5, 3)

    #VIDI KAKO I STA SA BAZOM DA SE POVEZE DA UZMES PODATKE OD VOZACA    


    def on_start(self):
        global counter
        random_taxi_stop = taxi_stops[randrange(0, len(taxi_stops))]
        self.driver = self.client.post('/api/drivers', json={
            'licensePlateNumber': license_plates.pop(0),
            'latitude': random_taxi_stop[0],
            'longitude': random_taxi_stop[1]
        }).json()
        counter=counter+1
        if(counter%3!=0):
            self.driving_to_start_point = True
            self.driving_the_route = False
            self.driving_to_taxi_stop = False
            self.departure = random_taxi_stop
            self.destination = start_and_end_points.pop(randrange(0, len(start_and_end_points)))
            self.get_new_coordinates()
        else:
            self.driving_to_start_point = False
            self.driving_the_route = False
            self.driving_to_taxi_stop = False
            self.departure = random_taxi_stop
            self.destination = random_taxi_stop
            self.get_new_coordinates()

    @task
    def update_vehicle_coordinates(self):
        if len(self.coordinates) > 0:
            new_coordinate = self.coordinates.pop(0)
            self.client.put(f"/api/drivers/{self.driver['id']}", json={
                'latitude': new_coordinate[0],
                'longitude': new_coordinate[1]
            })
        elif len(self.coordinates) == 0 and self.driving_to_start_point:
            self.end_ride()
            self.departure = self.destination
            while (self.departure[0] == self.destination[0]):
                self.destination = start_and_end_points.pop(randrange(0, len(start_and_end_points)))
            self.get_new_coordinates()
            self.driving_to_start_point = False
            self.driving_the_route = True
        elif len(self.coordinates) == 0 and self.driving_the_route:
            random_taxi_stop = taxi_stops[randrange(0, len(taxi_stops))]
            start_and_end_points.append(self.departure)
            self.end_ride()
            self.departure = self.destination
            self.destination = random_taxi_stop
            self.get_new_coordinates()
            self.driving_the_route = False
            self.driving_to_taxi_stop = True
        elif len(self.coordinates) == 0 and self.driving_to_taxi_stop:
            random_taxi_stop = taxi_stops[randrange(0, len(taxi_stops))]
            start_and_end_points.append(self.departure)
            self.end_ride()
            self.departure = random_taxi_stop
            self.destination = start_and_end_points.pop(randrange(0, len(start_and_end_points)))
            self.get_new_coordinates()
            self.driving_to_taxi_stop = False
            self.driving_to_start_point = True

    def get_new_coordinates(self):
        response = requests.get(f'https://routing.openstreetmap.de/routed-car/route/v1/driving/{self.departure[1]},{self.departure[0]};{self.destination[1]},{self.destination[0]}?geometries=geojson&overview=false&alternatives=true&steps=true')
        self.routeGeoJSON = response.json()
        self.coordinates = []
        for step in self.routeGeoJSON['routes'][0]['legs'][0]['steps']:
            self.coordinates = [*self.coordinates, *step['geometry']['coordinates']]
        self.ride = self.client.post('/api/rides', json={
            'routeJSON': json.dumps(self.routeGeoJSON),
            'rideState': 0,
            'driver': {
                'id': self.driver['id'],
                'licensePlateNumber': self.driver['licensePlateNumber'],
                'latitude': self.coordinates[0][0],
                'longitude': self.coordinates[0][1]
            } 
        }).json()

    def end_ride(self):
        self.client.put(f"/api/rides/{self.ride['id']}")
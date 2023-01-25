import requests
import json

from locust import HttpUser, task, between, events
from random import randrange


#Ovo treba da se dobavi iz baze
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


#Lista gde se samo vracaju svi automobili, ona moze da ostane ovde
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

counter = 1


@events.test_start.add_listener
def on_test_start(environment, **kwargs):
    pass
    # requests.delete('http://localhost:8000/api/rides')
    # requests.delete('http://localhost:8000/api/drivers')


class QuickstartUser(HttpUser):
    host = 'http://localhost:8000'
    wait_time = between(0.5, 3)
 

    def on_start(self):
        global counter

        print("USAO U STARTTTTTTTTTTTTTTTTTTTTTTTTTTTT")

        self.driver = self.client.get('/api/drivers/getDriver/'+str(counter)).json()
        self.driverSTARTEDRide = self.client.get('/api/rides/getDriversSTARTEDRide/'+str(self.driver['id'])).json()
        self.driverINPROGRESSRide = self.client.get('/api/rides/getDriversINPROGRESSRide/'+str(self.driver['id'])).json()
        if counter<3:
            counter=counter+1

        if(self.driverSTARTEDRide['rideState']=="STARTED"):
            print("Kreira VOznju za vozaca---")
            print(self.driver)
            self.driving_to_start_point = True
            self.driving_the_route = False
            self.departure = (self.driver['currentCoords']['latitude'],self.driver['currentCoords']['longitude'])
            self.destination = (self.driverSTARTEDRide['route']['startLocation']['latitude'],self.driverSTARTEDRide['route']['startLocation']['longitude'])
            self.get_new_coordinates()
        
        elif(self.driverINPROGRESSRide['rideState']=='IN_PROGRESS'):
            self.driving_to_start_point = False
            self.driving_the_route = True
            self.departure = (self.driver['currentCoords']['latitude'],self.driver['currentCoords']['longitude'])
            self.destination = (self.driverINPROGRESSRide['route']['endLocation']['latitude'],self.driverINPROGRESSRide['route']['endLocation']['longitude'])
            self.getCoordsForRideINPROGRESS()

        else:
            while(True):
                self.driverSTARTEDRide = self.client.get('/api/rides/getDriversSTARTEDRide/'+str(self.driver['id'])).json()
                if(self.driverSTARTEDRide['rideState']=="STARTED"):
                    self.driving_to_start_point = True
                    self.driving_the_route = False
                    self.departure = (self.driver['currentCoords']['latitude'],self.driver['currentCoords']['longitude'])
                    self.destination = (self.driverSTARTEDRide['route']['startLocation']['latitude'],self.driverSTARTEDRide['route']['startLocation']['longitude'])
                    self.get_new_coordinates()
                    break
                

    @task
    def update_vehicle_coordinates(self):
        if len(self.coordinates) > 0:
            new_coordinate = self.coordinates.pop(0)
            print(new_coordinate)
            self.client.put(f"/api/drivers/updateDriverLocation/{self.driver['id']}", json={
                'latitude':new_coordinate[1],
                'longitude':new_coordinate[0]
            })
        elif len(self.coordinates) == 0 and self.driving_to_start_point:
            self.end_ride()
            self.departure = self.destination
            self.destination = (self.driverSTARTEDRide['route']['endLocation']['latitude'],self.driverSTARTEDRide['route']['endLocation']['longitude'])
            self.getCoordsForRideSTARTED()
            self.driving_to_start_point = False
            self.driving_the_route = True

        elif len(self.coordinates) == 0 and self.driving_the_route:
            self.end_ride()
            self.departure = self.destination
            self.destination = (self.driver['currentCoords']['latitude'],self.driver['currentCoords']['longitude'])
            self.driving_to_start_point = False
            self.driving_the_route = False

        else:
            self.driverSTARTEDRide = self.client.get('/api/rides/getDriversSTARTEDRide/'+str(self.driver['id'])).json()
            if(self.driverSTARTEDRide['rideState']=="STARTED"):
                self.driver = self.client.get('/api/drivers/getDriver/'+str(self.driver['id'])).json()
                self.driving_to_start_point = True
                self.driving_the_route = False
                self.departure = (self.driver['currentCoords']['latitude'],self.driver['currentCoords']['longitude'])
                self.destination = (self.driverSTARTEDRide['route']['startLocation']['latitude'],self.driverSTARTEDRide['route']['startLocation']['longitude'])
                self.get_new_coordinates()


    def getCoordsForRideINPROGRESS(self):
        self.ride  = self.driverINPROGRESSRide
        self.routeGeoJSON = json.loads(self.driverINPROGRESSRide['route']['routeJSON'])
        self.coordinates = []
        if("routesIndex" not in self.routeGeoJSON):
            for step in self.routeGeoJSON['routes'][0]['legs'][0]['steps']:
                self.coordinates = [*self.coordinates, *step['geometry']['coordinates']]
        else:
            brojac = 0
            for step in self.routeGeoJSON['coordinates']:
                if(brojac%3==0):
                    self.coordinates.append([step['lng'],step['lat']])
                brojac=brojac+1
        

    def getCoordsForRideSTARTED(self):
        self.ride  = self.driverSTARTEDRide
        self.change_to_INPROGRESS()

        self.routeGeoJSON = json.loads(self.driverSTARTEDRide['route']['routeJSON'])
        self.coordinates = []
        if("routesIndex" not in self.routeGeoJSON):
            for step in self.routeGeoJSON['routes'][0]['legs'][0]['steps']:
                self.coordinates = [*self.coordinates, *step['geometry']['coordinates']]
        else:
            brojac = 0
            for step in self.routeGeoJSON['coordinates']:
                if(brojac%3==0):
                    self.coordinates.append([step['lng'],step['lat']])
                brojac=brojac+1

    def get_new_coordinates(self):
        response = requests.get(f'https://routing.openstreetmap.de/routed-car/route/v1/driving/{self.departure[1]},{self.departure[0]};{self.destination[1]},{self.destination[0]}?geometries=geojson&overview=false&alternatives=true&steps=true')
        self.routeGeoJSON = response.json()
        self.coordinates = []
        for step in self.routeGeoJSON['routes'][0]['legs'][0]['steps']:
            self.coordinates = [*self.coordinates, *step['geometry']['coordinates']]

        self.route = {
            'routeJSON': json.dumps(self.routeGeoJSON),
            'startLocation': {
                'latitude':self.departure[0],
                'longitude':self.departure[1]},
            'endLocation': {
                'latitude':self.destination[0],
                'longitude':self.destination[1]}
        }

        # 'rideState': 3, # znaci da je IN_PROGRESS
        # 'rideState': 4, # znaci da je DRIVING_TO_START

        self.ride = self.client.post('/api/rides/createRide', json={
            'route': self.route,
            'rideState': 4,
            'driver': self.driver['id']
        }).json()

    def end_ride(self):
        self.client.put(f"/api/rides/changeRide/{self.ride['id']}")
    
    def change_to_INPROGRESS(self):
        self.client.put(f"/api/rides/changeRideToPROGRESS/{self.ride['id']}")

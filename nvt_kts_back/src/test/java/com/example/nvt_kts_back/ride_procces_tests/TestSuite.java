package com.example.nvt_kts_back.ride_procces_tests;

import com.example.nvt_kts_back.ride_procces_tests.controller_tests.driver_controller_tests.DriverControllerGetDriverTests;
import com.example.nvt_kts_back.ride_procces_tests.controller_tests.registered_user_controller_tests.RegUserControllerGetUserStateBasedOnRideTests;
import com.example.nvt_kts_back.ride_procces_tests.controller_tests.ride_controller_tests.RideControllerGetUserInProgressRideTests;
import com.example.nvt_kts_back.ride_procces_tests.repository_tests.driver_repository_tests.DriverRepoFindByIdTests;
import com.example.nvt_kts_back.ride_procces_tests.repository_tests.registered_user_repository_tests.RegUserRepoFindByEmailTests;
import com.example.nvt_kts_back.ride_procces_tests.service_tests.driver_service_tests.DriverServiceFindByIdTests;
import com.example.nvt_kts_back.ride_procces_tests.service_tests.registered_user_service_tests.RegUserServiceGetByEmailTests;
import com.example.nvt_kts_back.ride_procces_tests.service_tests.ride_service_tests.RideServiceGetUsersInProgressRideTests;
import com.example.nvt_kts_back.service.ZakazivanjeVoznjeDriverServiceUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DriverControllerGetDriverTests.class,
        RegUserControllerGetUserStateBasedOnRideTests.class,
        RideControllerGetUserInProgressRideTests.class,
        DriverServiceFindByIdTests.class,
        RegUserServiceGetByEmailTests.class,
        RideServiceGetUsersInProgressRideTests.class,
        DriverRepoFindByIdTests.class,
        RegUserRepoFindByEmailTests.class
})
public class TestSuite {
}

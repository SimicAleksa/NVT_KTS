package com.example.nvt_kts_back.ride_procces_tests;

import com.example.nvt_kts_back.ride_procces_tests.controller_tests.driver_controller_tests.GetDriverTests;
import com.example.nvt_kts_back.ride_procces_tests.controller_tests.registered_user_controller_tests.GetUserStateBasedOnRideTests;
import com.example.nvt_kts_back.ride_procces_tests.controller_tests.ride_controller_tests.GetUserInProgressRideTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GetDriverTests.class,
        GetUserStateBasedOnRideTests.class,
        GetUserInProgressRideTests.class
})
public class TestSuite {
}

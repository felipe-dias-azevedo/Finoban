import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import LandingPage from './screens/LandingPage';
import Login from './screens/Login';
import ForgotPassword from './screens/ForgotPassword';
import Register from './screens/Register';
import Simulator from './screens/Simulator';
import Dashboard from './screens/Dashboard';
import Toast from 'react-native-toast-message'

const Tab = createBottomTabNavigator();

function Routes() {
    return <NavigationContainer>
        <Tab.Navigator>
            <Tab.Screen name="Home" component={LandingPage} />
            <Tab.Screen name="Register" component={Register} />
            <Tab.Screen name="Login" component={Login} />
            <Tab.Screen name="ForgotPassword" component={ForgotPassword} />
            <Tab.Screen name="Simulator" component={Simulator} />
            <Tab.Screen name="Dashboard" component={Dashboard} />
        </Tab.Navigator>
        <Toast ref={(ref) => Toast.setRef(ref)} />
    </NavigationContainer>
}

export default Routes;
import React from "react";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import { isAuthenticated } from "./auth";

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route
    {...rest}
    render={props =>
      isAuthenticated() ? (
        <Component {...props} />
      ) : (
        <Redirect to={{ pathname: "/", state: { from: props.location } }} />
      )
    }
  />
);

const Routes = () => (
  <BrowserRouter>
    <Switch>
      <Route exact path="/" component={props => <Login {...props} />} />
      <PrivateRoute paht="/home" component={props => <Home {...props} />} />
    </Switch>
  </BrowserRouter>
);

export default Routes;

import { Col, Row, Button, InputGroup, FormControl, Form } from "react-bootstrap";
import React, { Component } from "react";

import { withRouter } from "react-router-dom";
import UserService from "../services/UserService";
class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: ""
    };

    this.userService = new UserService();
  }

  async onLonginHandler() {
    try {
      await this.userService.login({
        email: this.state.email,
        password: this.state.password
      });
      this.props.history.push("/home");
    } catch (e) {
      alert("Credenciais Invalidas");
    }
  }
  async onSaveUser() {
    try {
      await this.userService.save({
        email: this.state.email,
        password: this.state.password
      });
    } catch (e) {
    
        alert("Usuario ja existe ou senha menor que 8 carateres");
    }
  }
  onChangeHander(e) {
    this.setState({ [e.target.name]: e.target.value });
  }
  render() {
    return (
      <Row className="justify-content-md-center">
        <Col xs lg={6}>
          <h1>Login</h1>

          <InputGroup className="mb-3">
            <InputGroup.Prepend>
              <InputGroup.Text id="basic-addon3">E-Mail</InputGroup.Text>
            </InputGroup.Prepend>
            <FormControl
              id="basic-url"
              aria-describedby="basic-addon3"
              onChange={e => this.onChangeHander(e)}
              value={this.state.email}
              name={"email"}
            />
          </InputGroup>

          <InputGroup className="mb-3">
            <InputGroup.Prepend>
              <InputGroup.Text id="basic-addon3">Password</InputGroup.Text>
            </InputGroup.Prepend>
            <FormControl
              id="basic-url"
              aria-describedby="basic-addon3"
              onChange={e => this.onChangeHander(e)}
              value={this.state.password}
              name={"password"}
              type={"password"}
            />
              
          </InputGroup>
          <Form.Text className="text-muted" style={{marginTop: "-1.3%", marginBottom: "2%"}}>
                senha deve pussir 8 caracteres
                </Form.Text>
          <Button variant="primary" onClick={() => this.onLonginHandler()}>
            Entrar
          </Button>
          <Button variant="primary" onClick={() => this.onSaveUser()} style={{float: "right"}}>
            Criar Usuário
          </Button>
        </Col>
      </Row>
    );
  }
}
export default withRouter(Login);

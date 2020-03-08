
import { Button, Modal, Form, Col } from "react-bootstrap";
import PropTypes from "prop-types";
import React, { Component } from "react";
import ProductService from "../services/ProductService";

class ProductModal extends Component {
  static propTypes = {
    title: PropTypes.string,
    data: PropTypes.object,
    onUpdateTable: PropTypes.func

  };
  constructor(props) {
    super(props);
    this.state = {
      show: false,
      coast: "",
      price: "",
      name: "",
      id: null
    };
    this.productServie = new ProductService();
  }

  componentDidMount() {
    if (this.props.data) {
      const { name, coast, price, id } = this.props.data;
      this.setState({ name, coast, price, id });
    }
  }

  handleClose = () => {
    this.setState({ show: false });
  };

  handleShow = () => {
    this.setState({ show: true });
  };

  onChangeHandler(e) {
   
    this.setState({ [e.target.name]: e.target.value });
    console.log("aqui", e.target.name);
  }

  async onSave() {
  let  requestBody = {
      coast: this.state.coast ? this.state.coast : null,
      price: this.state.price ? this.state.price : null,
      name: this.state.name ? this.state.name : null,
      id: this.state.id ? this.state.id : null
    }
    await this.productServie.save(requestBody);
    if(this.props.onUpdateTable){
      
    }
    this.props.onUpdateTable();
  }

  render() {
    return (
      <>
        <Button variant="primary" onClick={() => this.handleShow()}>
          {this.props.title}
        </Button>

        <Modal show={this.state.show}>
          <Modal.Header closeButton onClick={() => this.handleClose()}>
            <Modal.Title>Novo Produto</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form>
              <Form.Group controlId="fromProductName">
                <Form.Label>Nome do Produto</Form.Label>
                <Form.Control
                  type="name"
                  name="name"
                  placeholder="Ex.: Abacaxi"
                  value={this.state.name}
                  onChange={e => this.onChangeHandler(e)}
                />
                <Form.Text className="text-muted">
                  Insira o nome do item
                </Form.Text>
              </Form.Group>

              <Form.Row>
                <Form.Group as={Col} controlId="fromProductCoast">
                  <Form.Label>Custo</Form.Label>
                  <Form.Control
                    type="coast"
                    name="coast"
                    placeholder="Ex.: 1.23"
                    value={this.state.coast}
                    onChange={e => this.onChangeHandler(e)}
                  />
                  <Form.Text className="text-muted">
                    valor de compra do porduto <br/> Ex.: 10.0
                  </Form.Text>
                </Form.Group>

                <Form.Group as={Col} controlId="fromProductPrice">
                  <Form.Label>Preço</Form.Label>
                  <Form.Control
                    type="price"
                    name="price"
                    placeholder="Ex.: 1.23"
                    value={this.state.price}
                    onChange={e => this.onChangeHandler(e)}
                  />
                  <Form.Text className="text-muted">
                    valor de compra do porduto <br/> Ex.: 8.75 <br/> O preço não pode ser maior que o custo
                  </Form.Text>
                </Form.Group>
              </Form.Row>
            </Form>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => this.handleClose()}>
              Close
            </Button>
            <Button variant="primary" onClick={() => this.onSave()}>Save Changes</Button>
          </Modal.Footer>
        </Modal>
      </>
    );
  }
}
export default ProductModal;

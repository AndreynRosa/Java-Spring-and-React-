import React, { Component } from "react";
import { Container, Table, Button, Modal } from "react-bootstrap";
import ProductService from "../services/ProductService";
import { withRouter, Link } from "react-router-dom";
import ProductModal from "../component/ProductModal";

class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      products: []
    };
    this.productService = new ProductService();
  }
  async componentDidMount() {
    let resp = await this.productService.getPorducts();
    this.setState({ products: resp.data });
  }

  async onRemove(id) {
    await this.productService.remove(id);
    this.loadData();
  }
  logout() {
    console.log("logout");
    localStorage.setItem("userId", null);
    this.loadData();
  }

  loadData() {
    window.location.reload();
  }
  render() {
    return (
      <Container style={{ marginTop: "7%" }}>
        <ProductModal
          onUpdateTable={this.loadData}
          title={"Novo"}
        ></ProductModal>
        <Button onClick={() => this.logout()} style={{ float: "right" }}>
          Sair
        </Button>
        <Table striped bordered hover style={{ marginTop: "1%" }}>
          <thead>
            <tr>
              <th>Nome</th>
              <th>Preço</th>
              <th>Custo</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {this.state.products
              ? this.state.products.map(product => {
                  return (
                    <tr key={product.id}>
                      <td>{product.name}</td>
                      <td>R$ {product.price.toString().replace(".", ",")}</td>
                      <td>R$ {product.coast.toString().replace(".", ",")}</td>
                      <td style={{ maxWidth: 40 }}>
                        {" "}
                        <ProductModal
                          onUpdateTable={this.loadData}
                          data={product}
                          title={"Editar"}
                        ></ProductModal>
                        <Button
                          onClick={async () => this.onRemove(product.id)}
                          style={{ float: "right" }}
                        >
                          Remover
                        </Button>
                      </td>
                    </tr>
                  );
                })
              : " "}
          </tbody>
        </Table>
      </Container>
    );
  }
}
export default withRouter(Home);

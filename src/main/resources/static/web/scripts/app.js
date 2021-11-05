const mp = new MercadoPago('APP_USR-7eb0138a-189f-4bec-87d1-c0504ead5626',{locale :'es-AR'});


//Control de query string para pop-up de alerta
var urlParams = new URLSearchParams(window.location.search);
var params = Object.fromEntries(urlParams);
if (Object.keys(params).length > 0) {
  if (params.status === "approved") {
    swal({
      title: "Pago aprobado",
      text: "Tipo de pago " + params.payment_type + "\n" + params.external_reference + "\n" + "Numero de operación " + params.payment_id + "\n",
      icon: "success",
    });
  }
  else if (params.status === "in_process") {
    swal({
      title: "Pago pendiente",
      icon: "warning",
    });
  }
  else if (params.status === "rejected") {
    swal({
      title: "Pago rechazado",
      icon: "error",
    });
  }

};

const app = Vue.createApp({
  data() {
    return {
      products: [],
    }

  },

  //almacenamos del backend info del producto en products

  created() {

    var req = {
      method: 'GET',
      redirect: 'follow'
    };

    fetch("/api/products", req)
      .then(response => response.json())
      .then(result => this.products = result)
      .catch(error => console.log('error', error));
  },

  //buyPhone activa el proceso de compra
  methods: {
    buyPhone(phones) {
      var formdata = new FormData();
      formdata.append("phoneName", `${phones.name}`);
      formdata.append("phoneDescr", `${phones.description}`);
      formdata.append("phoneImg", `${phones.imgUrl}`);
      formdata.append("phoneQuant", "1");
      formdata.append("phonePrice", `${phones.price}`);


      var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
      };

      fetch("/mp", requestOptions)
        .then(response => response.text())
        .then(result => window.location.href = result)
        .catch(error => console.log('error', error));
  },
}
}
)


app.mount("#app")
<template>
  <div style="height: 100px"></div>
  <div class="container">
    <div class="row align-items-center justify-content-between">
      <div class="col p-5 align-items-center">
        <div style="width: 50%">
          <label class="form-label">Korisničko ime</label>
          <input
            v-model="loginData.username"
            type="text"
            placeholder=""
            class="form-control"
          />
          <br />
          <label class="form-label">Lozinka</label>
          <input
            v-model="loginData.password"
            type="password"
            placeholder=""
            class="form-control"
          />
          <br />
          <br /><br /><br />
          <button type="button" v-on:click="sendLogin" class="btn btn-primary">
            Prijavi se
          </button>
        </div>
      </div>
       <div class="col">
          <img
            src="../assets/doctor-selection.jpg"
            class="img-fluid"
            alt=""
          />
        </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Login",
  components: {},
  data: function () {
    return {
      loginData: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    sendLogin: function () {
      axios
        .post("http://localhost:8180/api/v1/auth/login", this.loginData)
        .then((response) =>{
          window.sessionStorage.setItem("jwt", response.data.jwt)
          this.$router.push('/');
        });
    },
  },
};
</script>
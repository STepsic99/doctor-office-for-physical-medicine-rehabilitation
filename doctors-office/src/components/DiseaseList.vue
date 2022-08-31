<template>
  <table style="text-align: left; margin-bottom: 2em">
    <tbody>
      <tr v-for="p in foundDiseases" :key="p.id">
        <td>{{ p.code }} - {{ p.name }}</td>
        <td>
          <i
            class="fa fa-times"
            aria-hidden="true"
            style="color: red; zoom: 2; margin-left: 60%; cursor: pointer"
            v-on:click="rechoosePerson(p)"
          ></i>
        </td>
      </tr>
    </tbody>
  </table>

    <div v-if="!personChosen" style="border: 1px solid;border-color:  #ced4da;padding:2em;width:60%;margin-bottom:2em">
  <table width="100%" style="border-collapse:separate; border-spacing:1em 0">
    <tr>
      <td>
        <form>
          <div class="input-group">
            <select
              class="form-select"
              v-model="searchOption"
              id="inputGroupSelect01"
            >
              <option value="1">Šifra bolesti</option>
              <option value="2">Naziv bolesti</option>
            </select>
            <input
              type="text"
              v-model="searchedCodeName"
              class="form-control"
            />
          </div>
        </form>
      </td>
      <td>
        <button
          type="button"
          class="btn btn-primary"
          v-on:click="searchPeople()"
        >
          Pretraži
        </button>
      </td>
      <td>
        <button
          type="button"
          class="btn btn-danger"
          v-on:click="abort()"
        >
          Odustani
        </button>
      </td>
    </tr>
  </table>
  <div v-if="showList">
    <table width="100%" style="border-collapse:separate; border-spacing:1em 1em">
      <tbody>
        <tr v-for="p in searchedDiseases" :key="p.id">
          <td>{{ p.code }} - {{ p.name }}</td>
          <td>
            <button
              v-if="!personChosen"
              type="button"
              class="btn btn-primary"
              v-on:click="choosePerson(p)"
            >
              Izaberi
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "DiseaseList",
  props: {
    closeSearch: Boolean,
  },
  data: function () {
    return {
      showList: false,
      searchOption: 1,
      searchedCodeName: "",
      foundDiseases: [],
      searchedDiseases: [],
      chosenDiseaseID: "",
      personChosen: this.closeSearch,
    };
  },
  watch: {
    closeSearch: function (value) {
      this.personChosen = value;
    },
  },
  methods: {
    searchPeople: function () {
      if (this.searchOption == 1) {
        axios.defaults.headers.common.Authorization =
          "Bearer " + window.sessionStorage.getItem("jwt");
        axios
          .get(
            "http://localhost:8180/api/v1/diseases?code=" +
              this.searchedCodeName
          )
          .then((response) => {
            this.searchedDiseases = response.data;
            this.showList = true;
          });
      } else {
        axios.defaults.headers.common.Authorization =
          "Bearer " + window.sessionStorage.getItem("jwt");
        axios
          .get(
            "http://localhost:8180/api/v1/diseases?name=" +
              this.searchedCodeName
          )
          .then((response) => {
            this.searchedDiseases = response.data;
            this.showList = true;
          });
      }
    },
    choosePerson: function (pickedDisease) {
      this.chosenDiseaseID = pickedDisease.id;
      for (var i = 0; i < this.searchedDiseases.length; i++) {
        if (this.searchedDiseases[i].id == this.chosenDiseaseID) {
          this.foundDiseases.push(this.searchedDiseases[i]);
          this.searchedDiseases = [];
          break;
        }
      }
      this.personChosen = true;
      this.searchedCodeName = "";
      this.$emit("chosenDisease", this.foundDiseases);
    },
    rechoosePerson: function (pickedDisease) {
      for (var i = 0; i < this.foundDiseases.length; i++) {
        if (this.foundDiseases[i].id == pickedDisease.id) {
          this.foundDiseases.splice(i, 1);
          break;
        }
      }
      this.searchedCodeName = "";
      this.$emit("chosenDisease", this.foundDiseases);
    },
    abort: function(){
        this.personChosen = true;
        this.$emit("chosenDisease", this.foundDiseases);
    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.thumb {
  margin-left: 15%;
  width: 80px;
  height: 80px;
  -o-object-fit: cover;
  object-fit: cover;
  overflow: hidden;
  border-radius: 20%;
}
</style>

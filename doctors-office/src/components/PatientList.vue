<template>
<div v-if="existingPatient">
<form style="margin-bottom:1em" v-if="!personChosen">
    <table width="100%">
      <tr>
        <td>
          <div class="input-group">
          <select class="form-select" v-model="searchOption" id="inputGroupSelect01">   
          <option value="1">Ime i prezime</option>
          <option value="2">JMBG</option>
        </select>
        <input type="text" v-model="searchedNameID" class="form-control">
        <input v-if="searchOption==1" type="text" v-model="searchedLastName" class="form-control">
      </div>
        </td>
        <td style="text-align:right">
        <button type="button" class="btn btn-primary" v-on:click="searchPeople()">Pretraži</button>
        </td>
        </tr>
    </table>    
</form>
<div v-if="showList">
<table style="width: 100%;">
  <tbody>
  <tr v-for="p in foundPeople" :key="p.id">
      <td>
        <div class="thumb">
                    <img class="img-fluid" src="../assets/person.jpg" alt="">
      </div>
      </td>
      <td>
        <div>
      <h5 class="mb-0" style="color:#0d6efd;margin-bottom:1em">{{p.firstName}} {{p.lastName}}</h5>
      <i class="fas fa-id-card" aria-hidden="true"></i> {{p.personalID}}
      </div>
      </td>
      <td>
      <i v-if="personChosen" class="fa fa-times" aria-hidden="true" style="color:red;zoom:2;margin-left:60%;cursor: pointer;" v-on:click="rechoosePerson()"></i>
      <button v-if="!personChosen" type="button" class="btn btn-primary" v-on:click="choosePerson(p)">Izaberi</button>
    </td>
     </tr>
  </tbody>
</table>
</div>
</div>

</template>

<script>
import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'
import axios from "axios";
export default {
  name: 'PatientList',
  components: {Datepicker},
  props: {
    existingPatient: Boolean
  },
   data: function(){
    return {
        showList: false,
        personChosen: false,
        searchOption:1,
        searchedNameID:'',
        searchedLastName:'',
        foundPeople:[],
        chosenPersonID:''
    }
  },
  methods: {
    searchPeople: function(){
      if(this.searchOption==1){
      axios.defaults.headers.common.Authorization =
        'Bearer ' + window.sessionStorage.getItem('jwt')
      axios
        .get('http://localhost:8180/api/v1/patients?first-name='+this.searchedNameID+'&last-name='+this.searchedLastName)
        .then((response) => {
          this.foundPeople = response.data
          this.showList=true;
        })
      } else{
        axios.defaults.headers.common.Authorization =
        'Bearer ' + window.sessionStorage.getItem('jwt')
      axios
        .get('http://localhost:8180/api/v1/patients?personal-ID='+this.searchedNameID)
        .then((response) => {
          this.foundPeople = response.data
          this.showList=true;
        })
      }
    },
     choosePerson: function(pickedPerson){
      this.chosenPersonID = pickedPerson.id;
      for (var i = 0; i < this.foundPeople.length; i++){
          if(this.foundPeople[i].id != this.chosenPersonID) 
            {this.foundPeople.splice(i,1)};
      }
      this.personChosen=true;
      this.$emit('chosenPerson', pickedPerson)
    },
    rechoosePerson: function(){
      this.personChosen=false;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.thumb {
   margin-left:15%;
    width: 80px;
    height: 80px;
    -o-object-fit: cover;
    object-fit: cover;
    overflow: hidden;
    border-radius: 20%;
}
</style>

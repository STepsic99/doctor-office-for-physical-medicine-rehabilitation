<template>
    <div style="margin-top: 60px;"></div>
    <div class="container" style="margin-bottom: 30px;">
    
        <div class="row">
          <div class="col">
            <h1>Calendar</h1>
            <FullCalendar :options="calendarOptions" />
            <br>
          </div>
        </div>
        
        
        <div class="row d-flex justify-content-center">               
        <button v-if="!createNewAppointment.formVisible && !showSelectedReservation" class="btn btn-primary mx-2 col-4" v-on:click="createNewAppointment.formVisible=true">Dodaj pregled</button>
        </div>
         
              
          
        

        <div v-if="createNewAppointment.formVisible" class="row d-flex justify-content-center" style="margin-top: 30px;">
          <div class="col-4">
            <div class="card">
              <div class="card-body">
                <h4 class="card-title">Kreirajte novi pregled</h4>
                <Datepicker v-on:click="showcreateNewAppointmentForm()" v-model="createNewAppointment.date" range></Datepicker>
                <select class="form-select" v-model="createNewAppointment.type" style="margin-top: 30px;">
                  <option value="EXAMINATION">PREGLED</option>
                  <option value="CONTROL_EXAMINATION">KONTROLNI PREGLED</option>
                </select> <br>

                <p class="card-text text-danger">{{createNewAppointment.msg}}</p>
                <button class="btn btn-danger m-1" v-on:click="createNewAppointment.formVisible=false">Odustani</button>
                <button :disabled="!createNewAppointment.valid" class="btn btn-success m-1" v-on:click="addNewAppointment()">Dodaj</button>
              </div>
            </div>
          </div>
        </div>
   

    </div>
</template>

<script>
import axios from "axios";


import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'

// FULL CALNEDAR
import '@fullcalendar/core/vdom' // solves problem with Vite
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import interactionPlugin, { Draggable } from '@fullcalendar/interaction'



export default {
  name: 'AppointmentCalendar',
  components: {
    FullCalendar,
    Datepicker
  },
  data: function(){
    return {
        terms: [],

        selectedReservation: {},
        showSelectedReservation: false,

        createNewAppointment:{
          date: [],
          formVisible: false,
          type: 'EXAMINATION',
          msg: '',
          valid: true,
        },

        calendarOptions: {
          plugins: [ dayGridPlugin, listPlugin, timeGridPlugin, interactionPlugin ],
          initialView: 'timeGridWeek', 
          selectable: true,
          headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
          },
          events: [
          ]
        }
    }
  },
  mounted: function(){
      this.calendarOptions.select = this.selectInCalendar;
      this.calendarOptions.eventClick = this.eventClickCalendar;
  },
  methods: {
    showcreateNewAppointmentForm: function(){
      this.createNewAppointment.msg = ''
      this.createNewAppointment.formVisible = true;
      this.createNewAppointment.valid = true;
    },
    selectInCalendar: function(selectedDate){
      if(this.showSelectedReservation) return;
      this.createNewAppointment.msg = ''
      this.createNewAppointment.valid = true;
      this.createNewAppointment.date[0] = new Date(selectedDate.start)
      this.createNewAppointment.date[1] = new Date(new Date(selectedDate.end) - 1000) 
      this.createNewAppointment.formVisible = true;
      if(this.overlap()){
          this.createNewAppointment.msg = 'Error, new term have overlap with other term'
          this.createNewAppointment.valid = false;
      }
    },
    addNewAppointment: function(){
       
    },
    overlap: function(){
        for(const event of this.calendarOptions.events){
            if(!((new Date(event.end) < this.createNewAppointment.date[0]) || (this.createNewAppointment.date[1] < new Date(event.start)))){
              console.log('OVERLAP')
              return true;
            }
        }
        return false;
    },
    eventClickCalendar: function(info){
        if(this.createNewAppointment.formVisible) return;
        
        let e = info.event._def.extendedProps
          this.loadDataSelectedReservation(e)   
    },
    loadDataSelectedReservation: function(event){
      this.selectedReservation = null;
      this.showSelectedReservation = false; 

     
    },
    closeSelectedReservation: function(){
       this.selectedReservation = null;
       this.showSelectedReservation = false; 
    }
  }
}
</script>


<style scoped>

</style>

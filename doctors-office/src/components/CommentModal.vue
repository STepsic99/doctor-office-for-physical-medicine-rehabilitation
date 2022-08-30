<template>
<transition name="modal-fade">
  <div v-if="renderComp" centered class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
        <slot name="header">
            Komentar
        </slot>
        <button
          type="button"
          class="btn-close"
          v-on:click="close()"
        >
          x
        </button>
      </header>

      <section class="modal-body">
        <slot name="body">
              {{this.$store.state.appointment.note}}
        </slot>
       </section>


    </div>
  </div>
</transition>
</template>
<script>
 import 'vue3-date-time-picker/dist/main.css'
export default {
  name: "CommentModal",
  props: ['renderComp'],
  data: function () {
    return {
     
    };
  },
  methods: {
    close() {
      this.$emit('close');
    }
  }
}
</script>
<style scoped>




  .modal-backdrop {
      
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
    
  }

  .modal {
    background: #FFFFFF;
    box-shadow: 2px 2px 20px 1px;
    overflow-x: auto;
    display: flex;
    flex-direction: column;
    height: 30%;
    width: 30%;
     position: relative;
  top: 10%;
  transform: translateY(-50%);
  
    
    
  }

  .modal-header,
  .modal-footer {
    padding: 15px;
    display: flex;
  }

  .modal-header {
    position: relative;
    border-bottom: 1px solid #eeeeee;
    color: black;
    justify-content: space-between;
    justify-content: center;
    align-items: center;
    font-weight: bold;
   
  }

  .modal-footer {
    border-top: 1px solid #eeeeee;
    flex-direction: column;
    justify-content: flex-end;
  }

  .modal-body {
    position: relative;
    padding: 20px 10px;
  }

  .btn-close {
    position: absolute;
    top: 0;
    right: 0;
    border: none;
    font-size: 20px;
    padding: 10px;
    cursor: pointer;
    font-weight: bold;
    color: #0d6efd;
    background: transparent;
  }

  .btn-green {
    color: white;
    background: #4AAE9B;
    border: 1px solid #4AAE9B;
    border-radius: 2px;
  }

  .modal-fade-enter {
    opacity: 0;
  }
  .modal-fade-leave-to {
    opacity: 0;
  }

  .modal-fade-enter-active .modal-fade-leave-active {
    transition: opacity .5s ease;
  }
  .modal-fade-leave-active {
    transition: opacity .5s ease;
  }
  
</style>
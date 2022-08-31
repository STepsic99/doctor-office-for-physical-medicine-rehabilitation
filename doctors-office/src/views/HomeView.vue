<template>
  <div >
      <div class="container-fluid" style="height: 40em;overflow-y: hidden;">



        <div style="height: 40em;overflow-y: hidden;" id="header-carousel" class="carousel slide carousel-fade" data-bs-ride="carousel">
            <div style="height: 40em;overflow-y: hidden;" class="carousel-inner">
                <div style="height: 40em;overflow-y: hidden;" class="carousel-item active">
                    <img  height="100%" width="100%" src="../assets/electricalstimulation.png" alt="Image">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                        <div class="p-3" style="max-width: 900px;">
                            <h5 class="text-white text-uppercase mb-3 animated slideInDown">mens sana in corpore sano</h5>
                            <h1 class="display-1 text-white mb-md-4 animated zoomIn">Visokokvalitetna Fizikalna Rehabilitacija </h1>
                          
                        </div>
                    </div>
                </div>
                <div style="height: 40em;overflow-y: hidden;" class="carousel-item">
                    <img  height="100%" width="100%" src="../assets/laserotherapy.png" alt="Image">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                        <div class="p-3" style="max-width: 900px;">
                            <h5 class="text-white text-uppercase mb-3 animated slideInDown">mens sana in corpore sano</h5>
                            <h1 class="display-1 text-white mb-md-4 animated zoomIn">Visokokvalitetna Fizikalna Rehabilitacija</h1>
                          
                        </div>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#header-carousel"
                data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#header-carousel"
                data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

        
    </div>
  </div>

  <div class="container-fluid py-5">
        <div class="container">
            <div class="row g-5">
                <div class="col-lg-7">
                    <div class="section-title mb-4">
                        <h5 class="position-relative d-inline-block text-primary text-uppercase">O nama</h5>
                        <h1 class="display-5 mb-0">Rumska najbolja fizijatrijska ordinacija</h1>
                    </div>
                    <h4 class="text-body fst-italic mb-4">Diam dolor diam ipsum sit. Clita erat ipsum et lorem stet no lorem sit clita duo justo magna dolore</h4>
                    <p class="mb-4">Tempor erat elitr rebum at clita. Diam dolor diam ipsum et tempor sit. Aliqu diam amet diam et eos labore. Clita erat ipsum et lorem et sit, sed stet no labore lorem sit. Sanctus clita duo justo et tempor eirmod magna dolore erat amet</p>
                    <div class="row g-3">
                        <div class="col-sm-6 wow zoomIn" data-wow-delay="0.3s">
                            
                            <h5 class="mb-3"><i class="fa fa-check-circle text-primary me-3"></i>Profesionalno osoblje</h5>
                        </div>
                        <div class="col-sm-6 wow zoomIn" data-wow-delay="0.6s">
                     
                            <h5 class="mb-3"><i class="fa fa-check-circle text-primary me-3"></i>Pogodne cene</h5>
                        </div>
                    </div>
                    <a href="appointment.html" class="btn btn-primary py-3 px-5 mt-4 wow zoomIn" data-wow-delay="0.6s">Pogledajte usluge</a>
                </div>
                <div class="col-lg-5" style="min-height: 500px;">
                    <div class="position-relative h-100">
                        <img class="position-absolute w-100 h-100 rounded wow zoomIn" data-wow-delay="0.9s" src="../assets/cert-doctor.jpg" style="object-fit: cover;">
                    </div>
                </div>
            </div>
        </div>
    </div>

    

</template>

<script>
// @ is an alias to /src


export default {
  name: 'HomeView',
  components: {
    
  }
}
</script>

<style scoped>

</style>

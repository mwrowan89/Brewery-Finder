<template>
  <div class="home">
    <div class="prompt">
      <h1>Welcome to the Ale Atlas</h1>
      <h1>Are you over 21?</h1><br>
      <span>
    <router-link to="/home">Yes</router-link>&nbsp;&nbsp;
    <a href="https://www.coca-cola.com/us/en" >No</a></span>
    </div>
    
    <div class="video">
      <video autoplay muted loop id="myVideo">
      <source src="../assets/Delirium Beer Pour(1080p).mp4" type="video/mp4" />
      </video>
    </div>
    
  </div>
</template>

<script>

</script>

<style scoped>
.home {
  color: white;
}
.home h1 {
  margin: 10px;
}

.prompt {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  width:fit-content;
  height: fit-content;
  padding: 25px;
  margin-top: 13vw;
  margin-left: 35vw;
  border: 2px solid white;
  background-color: rgba(223, 213, 213, 0.5);
  justify-content: center;
  align-items: center;
  ;
}

.prompt a {
  text-decoration: none;
  font-size: 30px;
  background-color: wheat;
  color: rgb(96, 90, 90);
  border-radius: 12px;
  padding: 10px;
}

.video {
  position: fixed;
  top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
    z-index: -1;
}

video {
  position: absolute;
    top: 50%;
    left: 50%;
    min-width: 100%;
    min-height: 100%;
    width: auto;
    height: auto;
    transform: translate(-50%, -50%);
}
</style>

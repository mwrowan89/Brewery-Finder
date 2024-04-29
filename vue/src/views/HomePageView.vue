<template>
    <div class="top">
        <div class="image"></div>
        <div class="text">
              <h1>BREWERY FINDER</h1>
              <p>Locate a brewery near you by rating or type</p>  
            </div>
    </div>
</template>

<style scoped>
@font-face {
  font-family: 'Lobster';
  font-style: normal;
  font-weight: normal;
  src: local('Lobster'), url('http://themes.googleusercontent.com/static/fonts/lobster/v4/NIaFDq6p6eLpSvtV2DTNDQLUuEpTyoUstqEm5AMlJo4.woff') format('woff');
}
@font-face {
    font-family: 'hughs';
    src: url('Fonts/Hughs.woff');
}

.top {
    background-color: white;
}

.text {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: rgb(170, 130, 78);
    background-color: rgba(255, 255, 255, 0.671);
    margin-top: -28vw;
    padding-bottom: 15px;
}
.text h1 {
    font-size: 100px;
    font-family: 'Lobster';
    font-weight: 300;
}
.text p {
    font-size: 50px;
    font-family: 'hughs';
}
.image {
    height: 60vh;
    width: 100vw;
    background-image: url("https://cdn.brewersassociation.org/wp-content/uploads/2020/06/small-brewery-demographics2.jpg");
    background-position: center;
    margin-top: 5%;
}

</style>
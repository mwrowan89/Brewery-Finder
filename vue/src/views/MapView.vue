<template>
  <div class="main">
    <div class="flex">
      <!-- Map Display here -->
      <div class="map-holder">
        <div id="map"></div>
      </div>
      <!-- Coordinates Display here -->
      <div class="dislpay-arena">
        <div class="coordinates-header">
          <h3>Current Coordinates</h3>
          <p>Latitude:</p>
          <p>Longitude:</p>
        </div>
        <div class="coordinates-header">
          <h3>Current Location</h3>
          <div class="form-group">
            <input
              type="text"
              class="location-control"
              :value="location"
              readonly
            />
            <button type="button" class="copy-btn">Copy</button>
          </div>
          <button type="button" class="location-btn">Get Location</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import mapboxgl from "mapbox-gl";
import MapboxGeocoder from "@mapbox/mapbox-gl-geocoder";
import "@mapbox/mapbox-gl-geocoder/dist/mapbox-gl-geocoder.css";
</script>

<style scoped>
.main {
  margin-top: 10vw;
  background-color: rgba(0, 0, 0, 0.48);
}
</style>

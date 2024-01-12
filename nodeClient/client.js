const axios = require('axios');

const teams = require('./teams.json')
const barthez = require('./player.json')


console.log("Post the teams")
axios.post('http://localhost:8080/api/team', teams)
  .then(function (response) {
    console.log("Post succesfull");
    getPlayer()
      .then(function(){
        getTeam(1)
        .then(function(){
          putPlayer()
          .then(function(){
            deletePlayer()
            .then(function(){
              getTeam(2);
            })
          })
        })
      })

  })
  .catch(function (error) {
    console.log(error);
  });


async function getPlayer() {
  try {
    const response = await axios.get('http://localhost:8080/api/player?id=9');
    console.log("Get the 9th player")
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }
}

async function getTeam(id) {
  try {
    const response = await axios.get(`http://localhost:8080/api/team?id=${id}`);
    console.log(`Get the team with id ${id}`);
    console.log(`Team name: ${response.data[0].name}`);
    console.log(response.data[0].players);
  } catch (error) {
    console.error(error);
  }
}


async function putPlayer() {
  try {

    const response = await axios.put('http://localhost:8080/api/player', barthez);
    console.log("Modification of the 6th player")
  } catch (error) {
    console.error(error);
  }
}

async function deletePlayer() {
  try {

    const response = await axios.delete('http://localhost:8080/api/player?id=7');
    console.log("Deletion of the 7th player")
  } catch (error) {
    console.error(error);
  }
}
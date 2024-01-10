const axios = require('axios');

const teams = require('./teams.json')


// console.log("Post the teams")
// axios.post('http://localhost:8080/api/team', teams)
//     .then(function (response) {
//         console.log("Post succesfull");
//         getPlayer();
        
//     })
//     .catch(function (error) {
//         console.log(error);
//     });

getPlayer();
getTeam();

async function getPlayer(){
    try {
        const response = await axios.get('http://localhost:8080/api/player?id=9');
        console.log("Get the 9th player")
        console.log(response.data);
      } catch (error) {
        console.error(error);
      }
}

async function getTeam(){
    try {
        
        const response = await axios.get('http://localhost:8080/api/team?id=1');
        console.log("Get the first team")
        console.log("Team name :" + response.data[0].name);
        console.log(response.data[0].players);
      } catch (error) {
        console.error(error);
      }
}


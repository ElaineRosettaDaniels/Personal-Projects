var currFunds = $("#tavernFunds").val();
var currFood = $("tavernFood").val();
var currDrinks = $("#tavernDrinks").val();
var currRepairs = $("#tavernRepairs").val();
var foodToAdd = $("#foodToAdd").val();
var drinksToAdd = $("#drinksToAdd").val();
var repairsToAdd = $("#repairsToAdd").val();
var promotionGold = $("#weekPromote").val();
var gpPerFood = 1;
var gpPerDrink = 1;
var gpPerRepair = 2;
var tendayExpense = 50;

function calcRollMod() {
    var rollMod = 10;
    if (currFood < 1) {
        rollMod - 25;
    }

    if (currDrinks < 1) {
        rollMod - 40;
    }

    if (currRepairs < 10) {
        rollMod - 25;
    } else if (currRepairs < 20) {
        rollMod - 20;
    } else if (currRepairs < 30) {
        rollMod - 15;
    } else if (currRepairs < 40) {
        rollMod - 10;
    } else if (currRepairs < 50) {
        rollMod - 5;
    }

    rollMod += promotionGold;
    return rollMod;
}

function rollForTenday() {
    var roll = Math.floor(Math.random() * 100) + 1;
    roll += rollMod;
    return roll;
}

$(document).ready(function(){



});
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

function calcRoll() {
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
    
    var roll = Math.floor(Math.random() * 100) + 1;
    roll += rollMod;
    return roll;
}

function tendayGold(roll) {
    var coffers = 0;
    var tR = 0;
    if (roll <= 20) {
        coffers -= tendayExpense * 1.5;
    } else if (roll <= 30) {
        coffers -= tendayExpense;
    } else if (roll <= 40) {
        coffers -= tendayExpense / 2;
    } else if (roll <= 60) {
        coffers += 0;
    } else if (roll <= 80) {
        profits = (Math.floor(Math.random() * 6) + 1) * 5;
    } else if (roll <= 90) {
        for (i = 0; i < 2; i++) {
            tR += Math.floor(Math.random() * 8) + 1;
        }
        coffers = tR * 5;
    } else if (roll >= 91) {
        for (i = 0; i < 3; i++) {
            tR += Math.floor(Math.random() * 10) + 1;
        }
        coffers = tR * 5;
    }

    currFunds += coffers;
}

$(document).ready(function(){



});
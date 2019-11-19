var currFunds = $("#tavernFunds").val();
var currFood = $("tavernFood").val();
var currDrinks = $("#tavernDrinks").val();
var currRepairs = $("#tavernRepairs").val();
var foodToAdd = $("#foodToAdd").val();
var drinksToAdd = $("#drinksToAdd").val();
var repairsToAdd = $("#repairsToAdd").val();
var currWeek = 1;
$("#tavernReportWeek").val(currWeek);
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
    var promotionGold = $("#weekPromote").val();
    rollMod = Number(rollMod) + Number(promotionGold);
    var minusPromo = Number(currFunds) - Number(promotionGold);
    $("#tavernFunds").val(minusPromo);
    currFunds = $("#tavernFunds").val();
    var roll = Math.floor(Math.random() * 100) + 1 + Number(rollMod);
    
    $("#tavernReportRoll").val(roll);
    return roll;
}

function tendayGold(roll) {
    var coffers = 0;
    var tR = 0;
    if (roll <= 20) {
        coffers -= Number(tendayExpense) * 1.5;
    } else if (roll <= 30) {
        coffers -= Number(tendayExpense);
    } else if (roll <= 40) {
        coffers -= Number(tendayExpense) / 2;
    } else if (roll <= 60) {
        coffers + 0;
    } else if (roll <= 80) {
        coffers += (Math.floor(Math.random() * 6) + 1) * 5;
    } else if (roll <= 90) {
        for (i = 0; i < 2; i++) {
            tR += Math.floor(Math.random() * 8) + 1;
        }
        coffers += Number(tR) * 5;
    } else if (roll >= 91) {
        for (i = 0; i < 3; i++) {
            tR += Math.floor(Math.random() * 10) + 1;
        }
        coffers += Number(tR) * 5;
    }

    var earnings = Number(coffers);
    var newFunds = Number(currFunds) + Number(coffers);
    $("#tavernFunds").val(newFunds);
    currFunds = $("#tavernFunds").val();
    $("#tavernReportEarnings").val(earnings);
}

$(document).ready(function(){

    

    $("#nextWeek").on("click", function(){
        tendayGold(calcRoll());
        currWeek++;
        $("#tavernReportWeek").val(currWeek);
    });

});
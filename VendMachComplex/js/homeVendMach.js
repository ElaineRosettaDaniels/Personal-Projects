var numDollars = 0;
var numQuarters = 0;
var numDimes = 0;
var numNickels = 0;
var currMoney = $("#currMoney").val();

function zeroChange() {
    numQuarters = 0;
    numDimes = 0;
    numNickels = 0;
}

var currentItemId;

function reply_click(clicked_id) {
    currentItemId = clicked_id;
}

$(document).ready(function(){

    $('#currMoney').val('0.00');
    
    $.ajax({
        type: 'GET',
        url:'http://tsg-vending.herokuapp.com/items',
        success: function(itemArray) {
            var blockNum = 1;
            
            // ? would make own function
            $.each(itemArray, function(index, item) {
                var itemDiv = $("#block" + blockNum);
                var card = '<div class="card" style="width: 13rem;">';
                card += '<button class="btn item" id="' + item.id +'" onClick="reply_click(this.id)">';
                card += '<p class="card-id">' + item.id + '</p> <br/>';
                card += '<h5 class="card-name"> <b>' +  item.name + '</b> </h5> <br/>';
                card += '<p class="card-price"> $' + (item.price).toFixed(2) + '</p> <br/>';
                card += '<p class="card-quantity">Quantity Left: ' + item.quantity + '</p>'; 
                card += '</button>';
                itemDiv.prepend(card);
                blockNum += 1;
            })

        }
    });

    $("#purchaseButton").on("click", function() {
        var currMoney = $("#currMoney").val();
        var currItem = $("#itemSelect").val();
        $.ajax({
            type: 'POST',
            url: 'http://tsg-vending.herokuapp.com/money/' + currMoney + '/item/' + currItem,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function(data, status) {
                numQuarters = data.quarters;
                numDimes = data.dimes;
                numNickels = data.nickels;
                $("#change").val(' ');
                $("#change").val(
                    numQuarters + ' Quarters, ' +
                    numDimes + ' Dimes, ' + 
                    numNickels + ' Nickels'
                );
                $("#messages").val('Thank you!');
                $("#currMoney").val('');
                $("#itemSelect").val('');
                zeroChange();
            },
            error: function(xhr, status, error) {
                var responseText = jQuery.parseJSON(xhr.responseText);
                var errorMessage = responseText.message;

                $("#messages").val(errorMessage);
            }
        });
    });

    $("#addDollar").on("click", function(){
        var currTotal = $("#currMoney").val();
        var newAmount = Number(currTotal) + 1.00;
        $('#currMoney').val(newAmount.toFixed(2));
    });
    $("#addQuarter").on("click", function(){
        var currTotal = $("#currMoney").val();
        var newAmount = Number(currTotal) + .25;
        $('#currMoney').val(newAmount.toFixed(2));
    });
    $("#addDime").on("click", function(){
        var currTotal = $("#currMoney").val();
        var newAmount = Number(currTotal) + .10;
        $('#currMoney').val(newAmount.toFixed(2));
    });
    $("#addNickel").on("click", function(){
        var currTotal = $("#currMoney").val();
        var newAmount = Number(currTotal) + .05;
        $('#currMoney').val(newAmount.toFixed(2));
    });

    $("#changeButton").on("click", function() {
        $("#change").val(' ');
        var currMoney = $("#currMoney").val();
        if (currMoney > 0.25) {
        numQuarters = Math.floor(currMoney / 0.25);
        currMoney = currMoney % 0.25;
        }
        if (currMoney > 0.10) {
        numDimes = Math.floor(currMoney / 0.10);
        currMoney = currMoney % 0.10;
        }
        numNickels = Math.ceil(currMoney / 0.05);

        $("#change").val(
            numQuarters + ' Quarters, ' +
            numDimes + ' Dimes, ' + 
            numNickels + ' Nickels'
        );
        zeroChange();
        $("#currMoney").val('');
    });

    $('#block1').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });
    $('#block2').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });
    $('#block3').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });
    $('#block4').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });
    $('#block5').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });
    $('#block6').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });
    $('#block7').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });
    $('#block8').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });
    $('#block9').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });
    $('#block10').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });
    $('#block11').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });
    $('#block12').click(function(event){
        $('#itemSelect').attr("value", currentItemId);
    });

});
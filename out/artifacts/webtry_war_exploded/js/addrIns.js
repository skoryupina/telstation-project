/**
 * Created by Ekaterina on 05.04.2015.
 */
var cityID=[];
//Получение выделенных записей
function getCityID(){
    var rb = document.getElementsByName("city");
    cityID = [];
    for(var i= 0; i<rb.length;i++){
        if(rb[i].checked){
            cityID.push(rb[i].id);
        }
    }
    console.log(cityID);
}

function checkIns()
{
    console.log("тут");
    getCityID();
    if (cityID.length>0)
    {
        var form = document.getElementById("insert");
        var params='';
        params+=cityID[0];
        console.log(params);
        form.paramIns.value=params;
        console.log(form.paramIns.value);
        form.submit();
    }
    else {
        window.alert("Не выбран соответствующий город!");
    }
}
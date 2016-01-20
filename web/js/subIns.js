/**
 * Created by Ekaterina on 05.04.2015.
 */
var addrID=[];
//Получение выделенных записей
function getAddrID(){
    var rb = document.getElementsByName("addr");
    addrID = [];
    for(var i= 0; i<rb.length;i++){
        if(rb[i].checked){
            addrID.push(rb[i].id);
        }
    }
    console.log(addrID);
}

function checkIns()
{
    console.log("тут");
    getAddrID();
    if (addrID.length>0)
    {
        var form = document.getElementById("insert");
        var params='';
        params+=addrID[0];
        console.log(params);
        form.paramIns.value=params;
        console.log(form.paramIns.value);
        form.submit();
    }
    else {
        window.alert("Не выбран адрес проживания!");
    }
}
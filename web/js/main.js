/**
 * Created by Ekaterina on 29.03.2015.
 */

var checkedID=[];
//Получение выделенных записей
function getID(){
    var cb = document.forms["table"].querySelectorAll('input[type="checkbox"]');
    checkedID = [];
    for(var i= 0; i<cb.length;i++){
        if(cb[i].checked){
            checkedID.push(cb[i].id);
        }
    }
    console.log(checkedID);
}

function checkUpd(){
    getID();
    var form = document.getElementById("update");
    var url = form.action;
    url=url.substring(url.lastIndexOf("/")+1,url.length);
    console.log(url);
    if (checkedID.length==1)
    {
        form.paramUpd.value=checkedID[0];
    }
    else{
        window.alert("Выберете для редактирования одну запись");
        switch(url){
            case "city.update":
                form.action="city.view";
                break;
            case "address.update":
                form.action="address.view";
                break;
            case "sub.update":
                form.action="sub.view";
                break;
              }
    }
    form.submit();
}

function checkDel()
{
    getID();
    if (checkedID.length>0)
    {
        var form = document.getElementById("delete");
        var params='';
        for(var i=0;i<checkedID.length;i++)
        {
            params+=checkedID[i]+'&';
        }
        form.paramDel.value=params;
        form.submit();
    }
    else {
        window.alert("Запись для удаления не выбрана!");
    }
}

function search()
{
    var tb = document.getElementById("nameForSearch");
    if (tb.value.length!=0)
    {
        var form = document.getElementById("search");
        form.paramSearch.value=tb.value;
        form.submit();
    }
    else
    {
        window.alert("Введите строку для поиска!");
    }
}

function searchTarif()
{
    var tbleft = document.getElementById("leftTarif").value;
    var tbright = document.getElementById("rightTarif").value;
    if (tbleft.length!=0&&tbright.length!=0) {
        if (parseFloat(tbleft) < parseFloat(tbright)) {
            var form = document.getElementById("searchTarif");
            var params = '';
            params = tbleft+ '&' + tbright + '&';
            form.paramTarif.value = params;
            form.submit();
        }
        else {
            window.alert("Левая граница больше правой!")
        }
    }
}


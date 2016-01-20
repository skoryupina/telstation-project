/**
 * Created by Ekaterina on 31.03.2015.
 */

function getSub()
{
    var tb = document.getElementById("nameForSearch");
    if (tb.value.length!=0)
    {
        var form = document.getElementById("search");
        console.log(form);
        form.paramSearch.value=tb.value;
        console.log(form.paramSearch.value);
        form.submit();
    }
    else {
        window.alert("Введите строку для поиска!");
    }
}

function searchByTel()
{
    var num = document.getElementById("num").value;
    console.log(document.getElementById("num"));
    console.log(num);
    if (num.length!=0) {
        if (parseFloat(num) >0) {
            var form = document.getElementById("searchTel");
            form.paramNum.value = num;
            form.submit();
        }
        else {
            window.alert("Введите корректный номер телефона!")
        }
    }
}


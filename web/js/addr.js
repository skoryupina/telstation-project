/**
 * Created by Ekaterina on 31.03.2015.
 */
function searchStreet()
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

function searchByFlat()
{
    var num = document.getElementById("num").value;
      if (num.length!=0) {
         if (parseFloat(num) >0) {
            var form = document.getElementById("searchFlat");
            form.paramNum.value = num;
            form.submit();
        }
        else {
            window.alert("Введите корректный номер квартиры!")
        }
    }
}


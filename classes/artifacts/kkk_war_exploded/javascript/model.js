i=0;j=0
$(document).ready(function () {

    $(".management").click(function () {
        // alert("hello world");
        if (i == 0) {

            $(".existinfo,.roughinfo,.pastinfo").show();

            i = 1;
        } else {
            $(".existinfo,.roughinfo,.pastinfo").hide();

            i = 0;
        }

    });

    $(".apply").click(function () {
        // alert("hello world");
        if (j == 0) {

            $(".f,.p").show();

            j = 1;
        } else {
            $(".f,.p").hide();

            j = 0;
        }

    });


})
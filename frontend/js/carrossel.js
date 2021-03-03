var element1 = document.querySelector(".slider li:nth-child(1) label");
var element2 = document.querySelector(".slider li:nth-child(2) label");
var element3 = document.querySelector(".slider li:nth-child(3) label");

element2.style.opacity = "0.5";
element3.style.opacity = "0.5";

function show(number) {
    if(number == 1) {
        element1.style.opacity = "1.0";
        element2.style.opacity = "0.5";
        element3.style.opacity = "0.5";
        setTimeout(show(2),2000)
    } else if (number == 2) {
        element2.style.opacity = "1.0";
        element1.style.opacity = "0.5";
        element3.style.opacity = "0.5";
        setTimeout(show(3),2000)
    } else {
        element3.style.opacity = "1.0";
        element1.style.opacity = "0.5";
        element2.style.opacity = "0.5";
        setTimeout(show(1),2000)
    }
}
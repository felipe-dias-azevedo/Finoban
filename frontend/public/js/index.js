var element1 = document.querySelector(".labels-img label:nth-child(2)");
var element2 = document.querySelector(".labels-img label:nth-child(4)");
var element3 = document.querySelector(".labels-img label:nth-child(6)");
var texto_imagem = document.querySelector(".top-left p")
element2.style.opacity = "0.5";
element3.style.opacity = "0.5";

function show(number) {
    if (number == 1) {
        element1.style.opacity = "1.0";
        element2.style.opacity = "0.5";
        element3.style.opacity = "0.5";
        texto_imagem.innerHTML = "Aqui temos a solução! pesquise por um imovel e faça um orçamento online com nos, sem perca de tempo com filas, sem complicações e melhor, tudo online!";
        document.getElementById("img-carrossel").src = "images/index-bg1.png"; 
        // setTimeout(show(2), 2000)
    } else if (number == 2) {
        element2.style.opacity = "1.0";
        element1.style.opacity = "0.5";
        element3.style.opacity = "0.5";
        
        texto_imagem.innerHTML = "Você também pode procuar seu imóvel apartir do nosso site, isso facilita para você já pedir o financiamento com o valor aproximado do imóvel.";
        document.getElementById("img-carrossel").src = "images/image 1.png"
        // setTimeout(show(3), 2000)
    } else {
        element3.style.opacity = "1.0";
        element1.style.opacity = "0.5";
        element2.style.opacity = "0.5";
        texto_imagem.innerHTML = "Já pensou poder fazer tudo que você precisa dentro do conforto da sua casa e sem burocracia? Se ainda não faz parte da FINOBAN então se cadastra e não perca mais tempo com filas em agências.";
        document.getElementById("img-carrossel").src = "images/image 2.png"
        // setTimeout(show(1), 2000)
    }
}



var header = document.querySelector(".screen1 header");
var nav = document.querySelector(".screen header nav a");
var footer = document.querySelector(".screen2 footer")
var section = document.querySelector(".screen2 footer section")
var screen2 = document.querySelector(".screen2");
var body = document.body;


function changeDarkMode() {
    // foobackgroundColor = "#000000";
    // element1.style.backgroundColor "#fff";
    // footer.style.backgroundColor = "#000000";
    // footer.style.color = "#000000";
    // header.style.color = "#fff";


    // secao2.style.color = "#fff";
    // carrossel_bolinhas.style.backgroundColor = "#fff";
    body.classList.toggle("dark-mode-body");
    header.classList.toggle("dark-mode-header");
    element1.classList.toggle("dark-mode-slider-label");
    element2.classList.toggle("dark-mode-slider-label");
    element3.classList.toggle("dark-mode-slider-label");
    footer.classList.toggle("dark-mode-footer");
    section.classList.toggle("dark-mode-footer");
    screen2.classList.toggle("dark-mode-color");
    nav.classList.toggle("dark-mode-nav")
    // element2.style.backgroundColor = "#fff";
    // element3.style.backgroundColor = "#fff";
}

//Seleccinando los elementos requeridos del html
const dropArea = document.querySelector(".drag-area"),
dragText = dropArea.querySelector("h2"),
button = dropArea.querySelector("button"),
input = dropArea.querySelector("input");
let file;

button.onclick = ()=>{
  input.click(); //Abre el input cuando hay click
}

input.addEventListener("change", function(){
  
  file = this.files[0]; //Si hay muchos archivos seleciona el primero
  dropArea.classList.add("active");
  processFile(file);
  dropArea.classList.remove("active");
});


//Si el usuario arrastra el archivo sobre DropArea
dropArea.addEventListener("dragover", (event)=>{
  event.preventDefault();
  dropArea.classList.add("active");
  dragText.textContent = "Suelta para subir el archivo";
});

//Si el usuario deja el archivo arrastrado desde DropArea
dropArea.addEventListener("dragleave", ()=>{
  dropArea.classList.remove("active");
  dragText.textContent = "Arrastra y suelta para subir el archivo";
});

//Si el usuario suelta el archivo en DropArea
dropArea.addEventListener("drop", (event)=>{
  event.preventDefault();
  file = event.dataTransfer.files[0]; //Si hay muchos archivos seleciona el primero
  processFile(file);
});

function processFile(file){
  
    let preview = document.getElementById("preview");
    if(preview.hasChildNodes()){
      while(preview.childNodes.length>=1){
        preview.removeChild(preview.firstChild);
      }
    }
    showFile(file);
}

function showFile(file){
  let fileType = file.type; //Obtiene el tipo de archivo
  let validExtensions = ["image/jpeg", "image/jpg", "image/png", "application/pdf", 
  "application/vnd.openxmlformats-officedocument.wordprocessingml.document", 
  "application/vnd.openxmlformats-officedocument.presentationml.presentation"]; //Añade las extensiones validas al array
  if(validExtensions.includes(fileType)){ //Si la extension es valida
    let fileReader = new FileReader();
    let id = `file-${Math.random().toString(32).substring(7)}`;

    fileReader.addEventListener('load', e =>{
      let fileURL = fileReader.result;
      let archivo = `
        <div id="${id}" class="file-container">
          <img src="${fileURL}" alt="${file.name}" width="50px">
          <div class="status">
            <span class="status-text">
              Cargando...
            </span>
          </div>
        </div>
      `;

      const html = document.querySelector("#preview").innerHTML;
      document.querySelector('#preview').innerHTML = archivo + html;
    });
      
    fileReader.readAsDataURL(file);

  }else{
    alert("La extensión del archivo no es válida");
    dropArea.classList.remove("active");
    dragText.textContent = "Arrastra y suelta para subir un archivo";
  }
}

/*Funcionalidad de SideBar */
function openNav() {
  document.getElementById("sideNavMenu").style.width = "250px";
}

/*Pone el width = 0 del sidebar*/
function closeNav() {
  document.getElementById("sideNavMenu").style.width = "0";
}

function openBiblioteca() {
  document.getElementById("itemBiblio1").style.display = "block";
  document.getElementById("itemBiblio2").style.display = "block";
  document.getElementById("itemBiblio3").style.display = "block";
}
function closeBiblioteca() {
  document.getElementById("itemBiblio1").style.display = "none";
  document.getElementById("itemBiblio2").style.display = "none";
  document.getElementById("itemBiblio3").style.display = "none";
}


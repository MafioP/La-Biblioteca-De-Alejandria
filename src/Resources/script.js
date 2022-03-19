//selecting all required elements
const dropArea = document.querySelector(".drag-area"),
dragText = dropArea.querySelector("h2"),
button = dropArea.querySelector("button"),
input = dropArea.querySelector("input");
let file;

button.onclick = ()=>{
  input.click(); //if user click on the button then the input also clicked
}

input.addEventListener("change", function(){
  //getting user select file and [0] this means if user select multiple files then we'll select only the first one
  file = this.files[0];
  dropArea.classList.add("active");
  showFile(file); //calling function
  dropArea.classList.remove("active");
});


//If user Drag File Over DropArea
dropArea.addEventListener("dragover", (event)=>{
  event.preventDefault(); //preventing from default behaviour
  dropArea.classList.add("active");
  dragText.textContent = "Suelta para subir el archivo";
});

//If user leave dragged File from DropArea
dropArea.addEventListener("dragleave", ()=>{
  dropArea.classList.remove("active");
  dragText.textContent = "Arrastra y suelta para subir el archivo";
});

//If user drop File on DropArea
dropArea.addEventListener("drop", (event)=>{
  event.preventDefault(); //preventing from default behaviour
  //getting user select file and [0] this means if user select multiple files then we'll select only the first one
  file = event.dataTransfer.files[0];
  showFile(file); //calling function
});

function showFile(file){
  let fileType = file.type; //getting selected file type
  let validExtensions = ["image/jpeg", "image/jpg", "image/png"]; //adding some valid extensions in array
  if(validExtensions.includes(fileType)){ //if user selected file is a valid file
    let fileReader = new FileReader(); //creating new FileReader object
    let id = `file-${Math.random().toString(32).substring(7)}`;

    fileReader.addEventListener('load', e =>{
      let fileURL = fileReader.result; //passing user file source in fileURL variable
      let archivo = `
        <div id="${id}" class="file-container">
          <img src="${fileURL}" alt="${file.name}" width="50">
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
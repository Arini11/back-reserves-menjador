// Call the dataTables jQuery plugin
$(document).ready(function() {
  carregarOrdres();
  $('#ordres').DataTable();
});

async function carregarOrdres() {
  const request = await fetch('api/orders/', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  });
  const ordres = await request.json();

  console.log(ordres);


  let llista = "";
  for(let o of ordres){
    let botoEliminar = '<a href="#" onclick="eliminarOrdre('+o.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
    let botoEditar = '<a href="#" onclick="eliminarOrdre('+o.id+')" class="btn btn-info btn-circle btn-sm"><i class="fas fa-info"></i></a>'
    //let tlf = u.telefon == null ? '-' : u.telefon;
    let ordre = '<tr><td>'+o.id+'</td><td>'+o.createdOn+'</td><td>'+o.modifiedOn+
    '</td><td>'+o.deliveryOn+'</td><td>'+o.delivered+'</td><td>'+o.user["username"]+'</td><td>'+
    botoEliminar+'</td><td>'+botoEditar+'</td></tr>';
    llista = llista + ordre;
  }


  document.querySelector('#ordres tbody').outerHTML = llista;
}
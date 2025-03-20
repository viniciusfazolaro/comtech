var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	return new bootstrap.Tooltip(tooltipTriggerEl)
})

var myModal = document.getElementById('myModal');
var bsModal = new bootstrap.Modal(document.getElementById('myModal'));
myModal.addEventListener('show.bs.modal', function (event) {
	// Botão que acionou o modal
	var button = event.relatedTarget;
	// Extrair informações dos atributos data-bs-*
	var id = button.getAttribute('data-bs-id');
	var name = button.getAttribute("data-bs-name");

	// Atualizar o conteúdo do modal
	var modalTitle = myModal.querySelector('.modal-title');
	var modalButton = myModal.querySelector('.modal-footer #delete');

	modalTitle.textContent = 'Exclusão do Método de Pagamento ' + name;
	modalButton.addEventListener('click', function(){
		deletePaymentMethod(button,id);
		bsModal.hide();
	});
});

function deletePaymentMethod(button, id){
	var row = button.parentNode.parentNode.parentNode;
	// button->span->td->tr

	let status = document.getElementById('status');

	// limpar a div
	status.innerHTML = '';
	status.classList.remove('alert-danger', 'alert-primary');

	let btn = document.createElement('button');
	btn.classList.add('btn-close');
	btn.type = 'button';
	btn.setAttribute('data-bs-dismiss', 'alert');
	btn.setAttribute('aria-label', 'close');

	var request = new XMLHttpRequest();
	request.open("GET","paymentMethodRegister?action=remove&method-id="+id,true);
	request.send();
	request.onreadystatechange = function() {
		if(this.status === 200 && this.readyState === 4) {
			var response = JSON.parse(this.responseText);
			if(response) {
				status.classList.add('alert', 'alert-primary', 'alert-dismissible', 'fade', 'show');
				status.setAttribute('role', 'alert');
				status.innerHTML = 'Método de pagamento deletado com sucesso.';
				row.parentNode.removeChild(row);
			} else {
				status.classList.add('alert', 'alert-danger', 'alert-dismissible', 'fade', 'show');
				status.setAttribute('role', 'alert');
				status.innerHTML = 'Não é possível excluir o método de pagamento. Existem ordens de serviço associadas.';
			}
				status.appendChild(btn);
		}
	}
}

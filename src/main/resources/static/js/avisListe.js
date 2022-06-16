
document.getElementById("logoutButton").addEventListener("click", (event) => {

	event.preventDefault();
	if (confirm("voulez-vous vraiment vous déconnecter?")) {
		window.location.href = '/logout';
	}

});
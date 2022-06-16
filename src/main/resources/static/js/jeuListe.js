
document.getElementById("logoutButton").addEventListener("click", (event) => {

	event.preventDefault();
	if (confirm("voulez-vous vraiment vous déconnecter?")) {
		window.location.href = '/logout';
	}

});

document.querySelectorAll(".deleteButton").forEach(button => {
	button.addEventListener("click", (event) => {
		console.log("hello");
		event.preventDefault();
		if (confirm("voulez-vous vraiment supprimer l'élément?")) {
			window.location.href = event.target.href;
		}

	});
});
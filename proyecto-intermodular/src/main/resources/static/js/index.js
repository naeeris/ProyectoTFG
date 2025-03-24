
    document.getElementById("toggleSidebar").addEventListener("click", function() {
    let sidebar = document.getElementById("sidebar");
    let content = document.getElementById("content");
    let header = document.getElementById("header");
    let button = document.getElementById("toggleSidebar");

    // Alternar clases para ocultar y expandir contenido
    sidebar.classList.toggle("sidebar-collapsed");
    content.classList.toggle("content-expanded");
    header.classList.toggle("header-expanded");

    // Cambiar icono y texto del botón
    if (sidebar.classList.contains("sidebar-collapsed")) {
    button.innerHTML = '<i class="bi bi-chevron-right"></i> Show';
} else {
    button.innerHTML = '<i class="bi bi-chevron-left"></i> Hide';
}
});

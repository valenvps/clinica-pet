const API = "http://localhost:8080";


// LOGIN
function fazerLogin() {
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    fetch(`${API}/usuarios/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, senha })
    })
    .then(res => {
        if (!res.ok) throw new Error("Login inválido");
        return res.json();
    })
    .then(data => {
        localStorage.setItem('usuarioLogado', JSON.stringify(data));
        window.location.href = 'dashboard.html';
    })
    .catch(() => alert("E-mail ou senha inválidos"));
}


// LISTAR PETS
function carregarPets() {
    const container = document.getElementById('listaPets');
    if (!container) return;

    fetch(`${API}/pets`)
    .then(res => res.json())
    .then(pets => {
        container.innerHTML = '';

        pets.forEach(pet => {
            container.innerHTML += `
                <div class="pet-card">
                    <div class="pet-info">
                        <h3>🐕 ${pet.nome}</h3>
                        <p>Raça: ${pet.raca} | Idade: ${pet.idade} anos | Peso: ${pet.peso} kg</p>
                    </div>
                    <div class="pet-actions">
                        <button class="btn-exames" onclick="verExames(${pet.id}, '${pet.nome}')">Ver Exames</button>
                        <button class="btn-deletar" onclick="deletarPet(${pet.id})">Excluir</button>
                    </div>
                </div>
            `;
        });
    });
}


// CADASTRAR PET
function cadastrarPet(event) {
    event.preventDefault();

    const usuario = JSON.parse(localStorage.getItem('usuarioLogado'));
    const nome = document.getElementById('nomePet').value;
    const raca = document.getElementById('racaPet').value;
    const idade = document.getElementById('idadePet').value;
    const peso = document.getElementById('pesoPet').value;

    fetch(`${API}/pets`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nome,
            raca,
            idade,
            peso,
            usuario: { id: usuario.id }
        })
    })
    .then(res => res.json())
    .then(() => {
        alert("Pet cadastrado!");
        window.location.href = 'meus-pets.html';
    });
}


// DELETAR PET
function deletarPet(id) {
    if (!confirm("Tem certeza?")) return;

    fetch(`${API}/pets/${id}`, {
        method: "DELETE"
    })
    .then(() => carregarPets());
}


// VER EXAMES DE UM PET
function verExames(petId, petNome) {
    localStorage.setItem('petSelecionadoId', petId);
    localStorage.setItem('petSelecionadoNome', petNome);
    window.location.href = 'exames.html';
}


// LISTAR EXAMES
function carregarExames() {
    const container = document.getElementById('listaExames');
    const petId = localStorage.getItem('petSelecionadoId');
    const petNome = localStorage.getItem('petSelecionadoNome');

    if (!container) return;

    const spanNome = document.getElementById('nomePetExame');
    if (spanNome && petNome) spanNome.textContent = petNome;

    document.getElementById('petIdExame').value = petId;

    fetch(`${API}/exames`)
    .then(res => res.json())
    .then(exames => {
        const filtrados = exames.filter(e => e.pet && e.pet.id == petId);

        if (filtrados.length === 0) {
            container.innerHTML = "<p>Nenhum exame encontrado</p>";
            return;
        }

        container.innerHTML = '';

        filtrados.forEach(e => {
            container.innerHTML += `
                <div class="exame-card">
                    <h4>${e.nome}</h4>
                    <p>${e.resultado}</p>
                    <p>Status: ${e.status}</p>
                </div>
            `;
        });
    });
}


// CADASTRAR EXAME
function cadastrarExame(event) {
    event.preventDefault();

    const nome = document.getElementById('tipoExame').value;
    const resultado = document.getElementById('descricaoExame').value;
    const petId = localStorage.getItem('petSelecionadoId');

    fetch(`${API}/exames`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nome,
            resultado,
            status: "Pronto",
            pet: { id: petId }
        })
    })
    .then(res => res.json())
    .then(() => {
        alert("Exame cadastrado!");
        carregarExames();
        document.getElementById('formCadastroExame').reset();
    });
}


// LOGOUT
function logout() {
    localStorage.removeItem('usuarioLogado');
    localStorage.removeItem('petSelecionadoId');
    localStorage.removeItem('petSelecionadoNome');
    window.location.href = 'index.html';
}


// CONTROLE DE PÁGINA
window.onload = function () {
    const usuarioLogado = localStorage.getItem('usuarioLogado');
    const pagina = window.location.pathname;

    if (!usuarioLogado && !pagina.includes('index.html')) {
        window.location.href = 'index.html';
    }

    if (pagina.includes('dashboard.html') && usuarioLogado) {
        const usuario = JSON.parse(usuarioLogado);
        const span = document.getElementById('nomeUsuario');
        if (span) span.textContent = usuario.email;
    }

    if (pagina.includes('meus-pets.html')) carregarPets();
    if (pagina.includes('exames.html')) carregarExames();
};

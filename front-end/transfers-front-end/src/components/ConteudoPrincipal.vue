<script lang="ts">
import ListaTransferencias from "./ListaTransferencias.vue";


export default {
    
    components: { ListaTransferencias },
    methods: {
        
    }
}
</script>

<template>
  <main class="conteudo-principal">
    <ListaTransferencias />
  </main>
</template>

<style scoped>
.conteudo-principal {
  padding: 2.5rem 7.5rem;
  border-radius: 3.75rem 3.75rem 0rem 0rem;
  background: var(--creme, #FFFAF3);
  color: var(--cinza, #444);

  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5rem;
}

.lista-texto {
  color: var(--coral, #F0633C);
  display: block;
  text-align: center;
  margin-bottom: 1.5rem;
}

.lista-transferencias {
  display: flex;
  justify-content: center;
  gap: 1rem 1.5rem;
  flex-wrap: wrap;
}

.lista-vazia {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  gap: 0.25rem;

  color: var(--coral, #F0633C);
  text-align: center;
}

@media only screen and (max-width: 1300px) {
  .conteudo-principal {
    padding: 2rem 3.75rem;
    gap: 3.5rem;
  }
}

@media only screen and (max-width: 767px) {
  .conteudo-principal {
    padding: 2rem 1.5rem;
    gap: 4rem;
  }
}
</style>
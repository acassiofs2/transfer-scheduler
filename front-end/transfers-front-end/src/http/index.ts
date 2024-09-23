import type ITransferencia from "@/interfaces/ITransferencia";

async function obterDadosURL<T>(url: string) {
    const resposta = await fetch(url);
    return resposta.json() as T;
  }
  
  export async function obterTransferencias() {
    return obterDadosURL<ITransferencia[]>('http://localhost:8080/api/transferences');
  }
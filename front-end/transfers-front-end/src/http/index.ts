import type ILista from "@/interfaces/ILista";
import type ITransferencia from "@/interfaces/ITransferencia";

async function obterDadosURL<T>(url: string) {
    const resposta = await fetch(url);
    return resposta.json() as T;
  }
  
  export async function obterTransferencias() {
    return obterDadosURL<ILista>('http://localhost:8080/api/transferences');
  }

  export async function criarTransferencia(obj: ITransferencia): Promise<Response> {
    const rawResponse = await fetch('http://localhost:8080/api/transferences', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(obj)
    });
    return rawResponse;
  }
export default interface ITransferencia {
    source_account: string;
    destination_account: string;
    amount: string;
    tax: string;
    transfer_date: string;
    is_active: boolean;
    user_id: string;
    created_at:string;
}
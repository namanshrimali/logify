export interface JWTFormat {
    token:string
}
export interface JWTPayload {
    sub: string;
    authority: string;
    exp: number;
    iat: number;
}
export interface StandModel {
    id: number;
    title: string;
    description: string;
    image: string;
    url: string;
    eventID : number;
    responsibleID : string;
}

export interface ScoreModel {
    id: number,
    title: string,
    sumVotes: number
}
export interface StandModel {
    id: number;
    title: string;
    description: string;
    image: string;
    url: string;
}

export interface StandWithScore {
    id: number,
    title: string,
    sumVotes: number
}
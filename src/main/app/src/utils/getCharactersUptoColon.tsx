export const getCharactersUptoColon = (input: string): string => {
    const colonIndex = input.indexOf(':')
    if(colonIndex === -1){
        return input;
    }
    return input.substring(0, colonIndex);
}
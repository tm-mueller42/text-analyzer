export interface CharacterValidator {
  name: string,
  validate(character:string) : boolean 
}

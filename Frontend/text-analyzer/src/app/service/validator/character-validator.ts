export interface CharacterValidator {
  characterType: string,
  characterTypeDefinition: string,
  validate(character:string) : boolean,
}

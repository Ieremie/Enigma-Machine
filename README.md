# Enigma-Machine
Encrypting and Decrypting messages

The EnigmaMahcine has multiple parts that are used to encode a message.The algorithm of it takes a letter and it first passes
through the plugboard,which encodes it with the help of the plugs(returns the end of the plug),then it goes to the first rotor
which uses his mapping to encode it(the mapping is the same principle for all rotors,it considers the letter A-0 and the letter Z-25),
then there is the second and the third rotor followed by the reflector(his encode method is simpler,it only maps the letter without 
adding or substracting),then it 'reflects' back and the same thing happens backwards and the letter encoded is the one out of the plugBoard.

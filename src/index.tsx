import { NativeModules } from 'react-native';

type LumosType = {
  multiply(a: number, b: number): Promise<number>;
  lumos(): void;
  nox(): void;
};

const { Lumos } = NativeModules;
export default Lumos as LumosType;

import { NativeModules } from 'react-native';

type LumosType = {
  multiply(a: number, b: number): Promise<number>;
};

const { Lumos } = NativeModules;

export default Lumos as LumosType;

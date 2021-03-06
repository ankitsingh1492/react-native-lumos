import * as React from 'react';
import { StyleSheet, View, Text, Button } from 'react-native';
import Lumos from 'react-native-lumos';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    Lumos.multiply(3, 7).then(setResult);
  }, []);

  return (
    <View style={styles.container}>
      <Button title="ON" onPress={() => Lumos.lumos()} />
      <Button title="OFF" onPress={() => Lumos.nox()} />
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});

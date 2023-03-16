import ssl
ssl._create_default_https_context=ssl._create_unverified_context

from tensorflow.keras.datasets import imdb
(train_input, train_target), (test_input, test_target)=imdb.load_data(num_words=500)

from sklearn.model_selection import train_test_split

train_input, val_input, train_target, val_target=train_test_split(train_input, train_target, test_size=0.2, random_state=42)

import numpy as np
lengths=np.array([len(x) for x in train_input])

from tensorflow.keras.preprocessing.sequence import pad_sequences
train_seq=pad_sequences(train_input, maxlen=100)
val_seq=pad_sequences(val_input,maxlen=100)

from tensorflow import keras
model=keras.Sequential()
model.add(keras.layers.SimpleRNN(8, input_shape=(100,500)))
model.add(keras.layers.Dense(1, activation='sigmoid'))
train_oh=keras.utils.to_categorical(train_seq)
val_oh=keras.utils.to_categorical(val_seq)

rmsprop=keras.optimizers.RMSprop(learning_rate=1e-4)
model.compile(optimizer=rmsprop, loss='binary_crossentropy', metrics=['accuracy'])
checkpoint_cb=keras.callbacks.ModelCheckpoint('best-simplernn-model.h5',save_best_only=True)
early_stopping_cb=keras.callbacks.EarlyStopping(patience=3,restore_best_weights=True)
history=model.fit(train_oh, train_target,epochs=100, batch_size=64, validation_data=(val_oh, val_target),callbacks=[checkpoint_cb, early_stopping_cb])

